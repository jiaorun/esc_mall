package com.esc.mall;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.xwpf.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * POI 解析word文件 工具类
 *
 * @author jiaorun
 * @date 2021/08/26 11:44
 **/
public class PoiUtils {

    public static void main(String[] args) throws Exception {
        File file = new File("C:\\Users\\lenovo\\Desktop\\预案基本信息-副本.docx");
        new PoiUtils().verifyWordFile(file);
    }

    /**
     * 校验文件格式
     *
     * @param file
     * @return void
     * @author jiaorun
     * @date 2021/08/26 11:47
     */
    public void verifyWordFile(File file) throws Exception {
        String fileName = file.getName();
        int lastIndexOf = fileName.lastIndexOf(".");
        if (lastIndexOf == -1) {
            throw new IllegalArgumentException("当前传入的文件格式不合法！");
        }
        //校验文件后缀
        InputStream is = new FileInputStream(file);
        String suffix = fileName.substring(lastIndexOf + 1);
            switch (suffix) {
                case "doc":
                    handlerByDocFile(is);
                    break;
                case "docx":
                    handlerByDocxFile(is);
                    break;
                default:
                    throw new IllegalArgumentException("不能解析的文档类型，请输入正确的word文档类型的文件！");
            }
    }

    /**
     * 解析doc文件
     *
     * @param is
     * @return void
     * @author jiaorun
     * @date 2021/08/26 12:10
     */
    public void handlerByDocFile(InputStream is) {
        HWPFDocument doc = null;
        try {
            doc = new HWPFDocument(is);
            Range range = doc.getRange();
            assembleResult(range);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            closeDocFile(doc);
        }
    }

    /**
     * 组装结果值
     * @author jiaorun
     * @date 2021/08/26 15:59
     * @param range
     * @return java.util.Map<java.lang.String,java.lang.String>
     */
    private Map<String, String> assembleResult(Range range) {
        Map<String, String> resultMap = new HashMap<>(5);
        for (int i = 0; i < range.numParagraphs(); i++) {
            Paragraph paragraph = range.getParagraph(i);    //段落
            String text = paragraph.text();
            int fontSize = paragraph.getCharacterRun(0).getFontSize();//字体大小
            boolean isBold = paragraph.getCharacterRun(0).isBold();//是否加粗
            //如果启用条件标题不是最后一个段落，则赋值为下一个段落
            if (fontSize == 28 && isBold && text.contains("启用条件") && i != range.numParagraphs() - 1) {
                resultMap.put("enableCondition", range.getParagraph(i + 1).text());
                break;
            } else if (fontSize == 28 && isBold && text.contains("启用条件") && i == range.numParagraphs() - 1) { //表明启用条件标题为最后一个段落
                resultMap.put("enableCondition", null);
                break;
            } else {
                Paragraph nextParagraph = range.getParagraph(i + 1);  //下一段落
                int nextFontSize = nextParagraph.getCharacterRun(0).getFontSize();  //下一段落字体大小
                boolean nextIsBold = nextParagraph.getCharacterRun(0).isBold();//下一段落是否加粗
                if (fontSize == 28 && isBold && nextFontSize == 21 && !nextIsBold) {  //筛选出特定的段落用于返回(这里字体大小为28并且加粗的段落为标题)
                    if (text.contains("适用对象")) {
                        resultMap.put("suitableObject", nextParagraph.text());
                        continue;
                    }
                    if (text.contains("预案目的")) {
                        resultMap.put("summary", nextParagraph.text());
                        continue;
                    }
                    if (text.contains("预案摘要")) {
                        resultMap.put("summary", nextParagraph.text());
                        continue;
                    }
                    if (text.contains("预案体系描述")) {
                        resultMap.put("systemDesc", nextParagraph.text());
                        continue;
                    }
                    if (text.contains("分类分级描述")) {
                        resultMap.put("classification", nextParagraph.text());
                        continue;
                    }
                    if (text.contains("上游预案")) {
                        resultMap.put("upStreamPlan", nextParagraph.text());
                        continue;
                    }
                    if (text.contains("下游预案")) {
                        resultMap.put("downStreamPlan", nextParagraph.text());
                        continue;
                    }
                }
            }
        }
        return resultMap;
    }

    /**
     * 解析docx文件
     *
     * @param is
     * @return void
     * @author jiaorun
     * @date 2021/08/26 12:07
     */
    public void handlerByDocxFile(InputStream is) throws IOException, InvalidFormatException {
        XWPFDocument xwpfDocument = new XWPFDocument(is);
        Iterator<IBodyElement> bodyElementsIterator = xwpfDocument.getBodyElementsIterator();
        List<Object> datas = new ArrayList<>();
        while (bodyElementsIterator.hasNext()) {
            IBodyElement bodyElement = bodyElementsIterator.next();
            String content = handlerByBodyType(bodyElement, bodyElement.getPartType());
            datas.add(content);
        }
        xwpfDocument.close();
        is.close();
        printAllDatas(datas);
    }

    /**
     * 关闭连接
     * @author jiaorun
     * @date 2021/08/26 16:00
     * @param hwpfDocument
     * @return void
     */
    public void closeDocFile(HWPFDocument hwpfDocument) {
        try {
            if (hwpfDocument != null) {
                hwpfDocument.close();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void printAllDatas(Collection<?> datas) {
        System.out.println(datas);
    }

    /**
     * 开始处理当前的身体元素
     *
     * @param bodyElement
     * @return java.lang.String
     * @author jiaorun
     * @date 2021/08/26 12:10
     */
    public String handlerByBodyElement(IBodyElement bodyElement) {
        String content = null;
        //用于处理XWPFParagraph
        if (bodyElement instanceof XWPFParagraph) {
            System.out.println("当前获取的元素类型为：XWPFParagraph");
            content = handlerXwpfParagraphType(bodyElement);
        }
        return content;
    }

    /**
     * 用于处理当前的XWPFParagraph类型的数据
     * @author jiaorun
     * @date 2021/09/02 16:37
     * @param bodyElement
     * @return java.lang.String
     */
    public String handlerXwpfParagraphType(IBodyElement bodyElement) {
        XWPFParagraph xwpfParagraph = (XWPFParagraph) bodyElement;
        BodyElementType elementType = xwpfParagraph.getElementType();
        String content = getStringByBodyElementType(xwpfParagraph, elementType);
        System.out.println("当前文本的内容为：" + content);
        return content;
    }

    /**
     * 通过当前的类型和元素进行处理
     *
     * @param xwpfParagraph
     * @param bodyElementType
     * @return java.lang.String
     * @author jiaorun
     * @date 2021/08/26 12:09
     */
    public String getStringByBodyElementType(XWPFParagraph xwpfParagraph, BodyElementType bodyElementType) {
        System.out.println(bodyElementType);//当前测试结果为：PARAGRAPH
        String content = "";
        switch (bodyElementType) {
            case CONTENTCONTROL:
                //如果使用的是文本控件
                break;
            case PARAGRAPH:
                //如果是段落的处理结果
                content = xwpfParagraph.getParagraphText();
                break;
            case TABLE:
                //如果当前的的元素部分为表格
                break;
            default:
                break;
        }
        return content;
    }

    /**
     * 通过身体类型处理
     *
     * @param bodyElement
     * @param partType
     * @return java.lang.String
     * @author jiaorun
     * @date 2021/08/26 12:08
     */
    public String handlerByBodyType(IBodyElement bodyElement, BodyType partType) {
        System.out.println("当前的BodyType为：" + partType);
        String content = null;
        switch (partType) {
            case CONTENTCONTROL:
                break;
            case DOCUMENT:
                content = handlerByBodyElement(bodyElement);
                break;
            case HEADER:
                break;
            case FOOTER:
                break;
            case FOOTNOTE:
                break;
            case TABLECELL:
                break;
            default:
                throw new IllegalArgumentException("there is no this document type !please check this type!");
        }
        return content;
    }

}
