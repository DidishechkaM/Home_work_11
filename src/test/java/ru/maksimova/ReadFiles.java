package ru.maksimova;

import com.codeborne.pdftest.PDF;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.nio.file.Files;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class ReadFiles {
    private final ClassLoader cl = ReadFiles.class.getClassLoader();
@Test
    void zipWithPdfXlsPdfTest() throws Exception{
        try (InputStream is = cl.getResourceAsStream("FilesZip.zip");
             ZipInputStream sis = new ZipInputStream(is)){
            ZipEntry entry;
            while ((entry = sis.getNextEntry())!= null) {
                switch (Files.getFileExtension(entry.getName())){
                    case "pdf" -> {
                        PDF pdf = new PDF(sis);
                        Assertions.assertEquals("Exercise.pdf",entry.getName());

                    }
                }
            }
        }






    }
}
