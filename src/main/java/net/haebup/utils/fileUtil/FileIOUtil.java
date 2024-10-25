package net.haebup.utils.fileUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import jakarta.servlet.ServletException;
import java.io.FileInputStream;
import java.io.OutputStream;
import jakarta.servlet.http.HttpServletResponse;


public class FileIOUtil {

    // 운영체제에 상관없이 경로 구분자 사용
    // private static final String BOARD_UPLOAD_DIR = "uploads" + File.separator + "board";
    // private static final String LECTURE_NOTICE_UPLOAD_DIR = "uploads" + File.separator + "lecture" + File.separator + "notice";
    // private static final String LECTURE_DETAIL_UPLOAD_DIR = "uploads" + File.separator + "lecture" + File.separator + "detail";
    private static final String BOARD_UPLOAD_DIR = "D:\\java7\\project\\onlineLecture\\src\\main\\webapp\\uploads\\board";
    private static final String LECTURE_NOTICE_UPLOAD_DIR = "D:\\java7\\project\\onlineLecture\\src\\main\\webapp\\uploads\\lecture\\notice";
    private static final String LECTURE_DETAIL_UPLOAD_DIR = "D:\\java7\\project\\onlineLecture\\src\\main\\webapp\\uploads\\lecture\\detail";

    
    // 첨부파일 업로드 메서드
    public static String uploadBoardAttachment(HttpServletRequest request, String fieldName)
            throws IOException, ServletException {
        return uploadFile(request, fieldName, BOARD_UPLOAD_DIR);
    }

    public static String uploadProfilePicture(HttpServletRequest request, String fieldName)
            throws IOException, ServletException {
        return uploadFile(request, fieldName, LECTURE_NOTICE_UPLOAD_DIR);
    }

    public static String uploadMessageAttachment(HttpServletRequest request, String fieldName)
            throws IOException, ServletException {
        return uploadFile(request, fieldName, LECTURE_DETAIL_UPLOAD_DIR);
    }

    private static String uploadFile(HttpServletRequest request, String fieldName, String subDir)
            throws IOException, ServletException {
        String uploadPath = request.getServletContext().getRealPath(File.separator + subDir);
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        Part filePart = request.getPart(fieldName);
        if (filePart == null) {
            System.out.println("파일 업로드 실패 : " + fieldName);
            return null;
        }

        String fileName = getSubmittedFileName(filePart);

        if (fileName != null && !fileName.isEmpty()) {
            String uniqueFileName = UUID.randomUUID().toString() + "_" + fileName;
            String filePath = uploadPath + File.separator + uniqueFileName;

            filePart.write(filePath);
            return subDir + File.separator + uniqueFileName; // 웹 경로 반환
        }
        return null;
    }

    // 파일 다운로드 메서드
    // 쓸때 이렇게 쓰면됩니다.
    // @GetMapping("/download")
    // public void downloadFile(HttpServletResponse response) {
    //      String filePath = "path/to/your/file.pdf";
    //      String fileName = "downloadedFile.pdf";
    //      try {
    //          FileIOUtil.downloadFile(response, filePath, fileName);
    //      } catch (IOException e) {
    // // 에러 처리
    //      }
    // }
    public static void downloadFile(HttpServletResponse response, String filePath, String fileName) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new IOException("파일이 존재하지 않습니다. : " + filePath);
        }

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentLength((int) file.length());

        try (FileInputStream inputStream = new FileInputStream(file);
                OutputStream outputStream = response.getOutputStream()) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.flush();
        }
    }

    private static String getSubmittedFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

}
