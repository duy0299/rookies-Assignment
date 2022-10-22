package com.rookies.assignment.plugins;

import com.rookies.assignment.dto.response.ResponseDto;
import com.rookies.assignment.exceptions.ResourceFoundException;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
@NoArgsConstructor
public class CreateImage {

    public  ResponseDto<String> createOneFilesAvatar(MultipartFile avatar, String folderPath) {
//        Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));
//        Path staticPath = Paths.get("src\\main\\resources\\static");
//        Path pathRoot = CURRENT_FOLDER.resolve(staticPath);
//        String pathUpload = pathRoot.toString() + folderPath;
        Path root = Paths.get(folderPath);

        File uploadRootDir =  new File(root.toString());
        if(!uploadRootDir.exists()) {
            uploadRootDir.mkdirs();
        }
        try {
            StringBuffer name = new StringBuffer(avatar.getOriginalFilename());
            String fileNamea = avatar.getOriginalFilename();
            if(name.length() >= 50) {
                fileNamea = name.substring(name.length() - 30);
            }
            System.out.println(name);
            File serverFile = new File(uploadRootDir.getAbsoluteFile() + File.separator + fileNamea);
            System.out.println();
            while (true) {
                if(!serverFile.exists()) {
                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));

//					create a image
                    stream.write(avatar.getBytes());
                    stream.close();
                    break;
                }
                if(name.length() > 11) {
                    name = new StringBuffer(name.substring(name.length() - 10));
                }
                fileNamea = radomString(20)  + name.toString();
                serverFile = new File(uploadRootDir.getAbsoluteFile() + File.separator + fileNamea);
            }
            System.out.println("test: "+serverFile.getAbsolutePath());
            System.out.println("length: "+serverFile.getAbsolutePath().length());
            return new ResponseDto<String>("00", "thành công",serverFile.getAbsolutePath()) ;

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public ResponseDto<List<String>> createListFilesImage(List<MultipartFile> images, String folderPath) {
// prepare the path to the folder to be saved And check if the file is already there or not
        Path root = Paths.get(folderPath);
        File uploadRootDir =  new File(root.toString());
        if(!uploadRootDir.exists()) {
            uploadRootDir.mkdirs();
        }

        List<String> result = new ArrayList<>();
        for (MultipartFile image : images) {
            try {
                StringBuffer name = new StringBuffer(image.getOriginalFilename());
                String fileName = image.getOriginalFilename();
                if(name.length() >= 50) {
                    fileName = name.substring(name.length() - 30);
                }

                File serverFile = new File(uploadRootDir.getAbsoluteFile() + File.separator + fileName);

//				xát định tên file có bị trùng hay ko
                while (true) {
                    if(!serverFile.exists()) {
                        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
//						create 1 list image
                        stream.write(image.getBytes());
                        stream.close();
                        break;
                    }
                    if(name.length() > 11) {
                        name = new StringBuffer(name.substring(name.length() - 10));
                    }
                    fileName = radomString(20) + name.toString();
                    serverFile = new File(uploadRootDir.getAbsoluteFile() + File.separator + fileName);
                }
                String urlAvatar = root.toString() + fileName;
                System.out.println(urlAvatar);
                result.add(urlAvatar);

            } catch (Exception e) {
                System.out.println(e);
                throw new ResourceFoundException("lỗi hình ảnh ");
            }
        }

        return new ResponseDto<List<String>>(result);
    }



    public String radomString(int numberOfCharactor) {
        String alpha = "abcdefghijklmnopqrstuvwxyz"; // a-z
        String digits = "0123456789"; // 0-9
        String ALPHA_NUMERIC = alpha + digits;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numberOfCharactor; i++) {
            int number = randomNumber(0, ALPHA_NUMERIC.length() - 1);
            char ch = ALPHA_NUMERIC.charAt(number);
            sb.append(ch);
        }
        return sb.toString();
    }

    private static int randomNumber(int min, int max) {
        Random generator = new Random();
        return generator.nextInt((max - min) + 1) + min;
    }

}
