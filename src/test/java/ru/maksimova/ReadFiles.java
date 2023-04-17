package ru.maksimova;


import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import net.lingala.zip4j.ZipFile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;


public class ReadFiles {
    private static final  ClassLoader cl = ReadFiles.class.getClassLoader();



    @BeforeAll
    static void beforeAll() throws IOException, URISyntaxException {
         extractThem("PlainZip.zip");
    }

        static void extractThem (String archiveName) throws IOException, URISyntaxException {

            URL resource = cl.getResource(archiveName);
            File zipFile = Paths.get(resource.toURI()).toFile();
            String extractionPath = zipFile.getParent();
            ZipFile filesZip = new ZipFile(zipFile);
            filesZip.extractAll(extractionPath);
        }
    @Test
    void csvWithZip4j() throws IOException, URISyntaxException, CsvException {

        List<String[]> content;

        try (InputStream is = cl.getResourceAsStream("qaguru.csv");
             InputStreamReader isr = new InputStreamReader(is)) {
            CSVReader csvReader = new CSVReader(isr);
            content = csvReader.readAll();
        }

        String[] stringArray1 = content.get(1);

        Assertions.assertEquals(
                "JUnit5",
                stringArray1[1]
        );

    }


    @Test
        void pdfParseTest () throws Exception {

            URL pdfURL = cl.getResource("junit-user-guide-5.9.2.pdf");

            System.out.println(pdfURL.toString());

            PDF pdf = new PDF(pdfURL);

            Assertions.assertEquals(
                    "Stefan Bechtold, Sam Brannen, Johannes Link, Matthias Merdes, Marc Philipp, Juliette de Rancourt, Christian Stein",
                    pdf.author
            );
        }

        @Test
        void xlsParseTest () throws Exception {

            URL xlsURL = cl.getResource("teachers.xls");

            System.out.println(xlsURL.toString());

            XLS xls = new XLS(xlsURL);

            Assertions.assertTrue(
                    xls.excel.getSheetAt(0).getRow(3).getCell(2).getStringCellValue()
                            .startsWith("1. Суммарное количество часов планируемое на штатную по всем разделам плана")
            );
        }
    }




