package com.fogatta.utility;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {

    public static void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException{

        // Obtengo la ruta del directorio a crear
        Path uploadPath = Paths.get(uploadDir);

        // Creo el directorio si este no existe
        if(!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }

        // Guardo el archivo cargado desde el objero MultipartFile
        // en un archivo en el sistema de archivos
        try(InputStream inputStream = multipartFile.getInputStream()){
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe){
            throw new IOException("Could not save image file: " + fileName, ioe);
        }

    }
    
}
