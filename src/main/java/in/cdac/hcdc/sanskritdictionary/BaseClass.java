package in.cdac.hcdc.sanskritdictionary;

import in.cdac.hcdc.hocr4j.Page;
import in.cdac.hcdc.hocr4j.Paragraph;
import in.cdac.hcdc.hocr4j.dom.HocrParser;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Jyoti Patil (jyotip@cdac.in)
 */
public class BaseClass {

    BufferedWriter bw1 = null;
    BufferedWriter bw2 = null;
    FileWriter fw = null;
    int lastIndex = 0;

    public static void main(String[] args) {
        BaseClass baseClass = new BaseClass();
        File hocrFile = new File("C:\\Users\\Jyoti\\Pictures\\sanskrit-dictionary\\1519_1.hocr");
//        File hocrFile = new File("D:\\Jyoti-backup\\project-folders\\sanskritDictionary\\data\\1519_1.html");
        baseClass.parseHocrFile(hocrFile);
    }

    public List<Page> parseHocrFile(File file) {
        List<Page> pages = null;
        try {
            int i = 0;
            File f1 = new File("C:\\Users\\Jyoti\\Pictures\\sanskrit-dictionary\\output.txt");
            File f2 = new File("C:\\Users\\Jyoti\\Pictures\\sanskrit-dictionary\\words.txt");
            bw1 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f1), StandardCharsets.UTF_8));
            bw2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f2), StandardCharsets.UTF_8));
            String contents = FileUtils.readFileToString(file, "UTF-8");
            pages = HocrParser.parse(contents);
            for (Page page : pages) {
                List<Paragraph> praList = page.getAllParagraphs();
                for (Paragraph paragraph : praList) {
                    String paraText = readParaText(paragraph, bw2);
                    bw1.write("Paragraph :: " + i++);
                    bw1.write("\n");
                    bw1.write(paraText);
                    bw1.write("\n");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (bw1 != null) {
                    bw1.close();
                }
                if (bw2 != null) {
                    bw2.close();
                }
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return pages;
    }

    public String readParaText(Paragraph para, BufferedWriter bw2) throws IOException {
        String paraText = "";
        List<String> lines = para.getAllLinesAsString();
        for (String line : lines) {
            String[] words = line.split(" ");
            for (String word : words) {
                if (!word.trim().isEmpty()) {
                    paraText += word.trim() + " ";
                }
            }
        }
        String[] wordList = paraText.split(" ");
        // this is to get sanskrit main word
//        bw2.write(wordList[0]);
        // to get transliteration of the main word
        String translation = getTransliteration(wordList);
        String partOfSpeech = getPartOfSpeech(wordList, lastIndex);
        String subtype = getsubtype(wordList, lastIndex);
        String cmpltPartOfSpeech = partOfSpeech + " " + subtype;
        String meaning = getEngMeanig(wordList);
        bw2.write("\n");
        bw2.write(wordList[0] + "    ||  " + translation + " || " + cmpltPartOfSpeech + " || " + meaning);
        bw2.write("\n");
        return paraText;
    }

    public String getTransliteration(String[] wordList) {
        String translation = "";
        int startIndex = 0;
        int endIndex = 0;
//        String[] wordList = paraText.split(" ");
        for (int i = 1; i < wordList.length; i++) {

            // this is to check opening brackets
            if (wordList[i].matches("[(]") && startIndex == 0) {
                startIndex = i;
            }
            // This is to check closing brackest 
            if (wordList[i].matches("[)]") && startIndex != 0 && endIndex == 0) {
                endIndex = i;
            }

            // This is to check transliteration in "(word)" form
            if (wordList[i].matches("[(][a-z-]+[)]")) {
                startIndex = i;
                endIndex = i;
            }

            //This is to check  transliteration whih is in the form "(word"
            if (wordList[i].matches("[(][a-z-]+") && startIndex == 0) {
                startIndex = i;
            }

            //This is to check  transliteration whih is in the form "word)"
            if (wordList[i].matches("[a-z-]+[.]*[)]") && startIndex != 0 && endIndex == 0) {
                endIndex = i;
            }
        }
        if (startIndex != 0 && endIndex != 0) {
            for (int i = startIndex; i <= endIndex; i++) {
                translation += wordList[i].trim();
            }
        }
        lastIndex = endIndex;
        return translation;
    }

    public String getPartOfSpeech(String[] wordList, int LastIndex) {
        String type = "";
        if ((wordList[lastIndex + 1]).contains(")") || (wordList[lastIndex + 1]).contains("(")) {
            type = wordList[LastIndex + 2];
            lastIndex = LastIndex + 2;

        } else {
            type = wordList[LastIndex + 1];
            lastIndex = LastIndex + 1;
        }
        return type;
    }

    public String getsubtype(String[] wordList, int LastIndex) {
        String type = "";
        int start = 0;
        int end = 0;
        if (wordList.length >= 4) {
            if ((wordList[LastIndex + 3]).contains("[")) {
                start = LastIndex + 3;
                if (end == 0) {
                    for (int i = start; i < wordList.length; i++) {
                        if ((wordList[i]).contains("]")) {
                            end = i;
                            lastIndex = i;
                        }
                    }
                }
                for (int i = start; i <= end; i++) {
                    type += wordList[i];
                }
            }
            if ((wordList[LastIndex + 2]).contains("[")) {
                start = LastIndex + 2;
                if (end == 0) {
                    for (int i = LastIndex + 2; i < wordList.length; i++) {
                        if ((wordList[i]).contains("]")) {
                            end = i;
                            lastIndex = i;
                        }
                    }
                }
                for (int i = start; i <= end; i++) {
                    type += wordList[i];
                }
            }
        }
        return type;
    }

    public String getEngMeanig(String[] wordList) {
        String meaning = "";
        for (int i = lastIndex + 1; i < wordList.length; i++) {
            meaning += wordList[i] + " ";
        }
        return meaning;
    }
}
