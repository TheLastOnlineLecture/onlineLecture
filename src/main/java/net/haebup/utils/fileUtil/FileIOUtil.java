package net.haebup.utils.fileUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import jakarta.servlet.ServletException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import jakarta.servlet.http.HttpServletResponse;


public class FileIOUtil {

    // 운영체제에 상관없이 경로 구분자 사용
    private static final String BOARD_UPLOAD_DIR = "resources/uploads/board";
    private static final String LECTURE_NOTICE_UPLOAD_DIR = "resources/uploads/lecture/notice";
    private static final String LECTURE_DETAIL_UPLOAD_DIR = "resources/uploads/lecture/detail";
    // private static final String BOARD_UPLOAD_DIR = "D:\\java7\\project\\onlineLecture\\src\\main\\webapp\\uploads\\board";
    // private static final String LECTURE_NOTICE_UPLOAD_DIR = "D:\\java7\\project\\onlineLecture\\src\\main\\webapp\\uploads\\lecture\\notice";
    // private static final String LECTURE_DETAIL_UPLOAD_DIR = "D:\\java7\\project\\onlineLecture\\src\\main\\webapp\\uploads\\lecture\\detail";

    
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
        String applicationPath = request.getServletContext().getRealPath("");
        String uploadPath = applicationPath + File.separator + subDir;
        
        System.out.println("업로드 경로: " + uploadPath);
        
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            boolean created = uploadDir.mkdirs();
            System.out.println("디렉토리 생성 결과: " + created);
        }
        
        Part filePart = request.getPart(fieldName);
        if (filePart != null && filePart.getSize() > 0) {
            String fileName = getSubmittedFileName(filePart);
            String uniqueFileName = UUID.randomUUID().toString() + "_" + fileName;
            String filePath = uploadPath + File.separator + uniqueFileName;
            
            System.out.println("파일 저장 경로: " + filePath);
            
            filePart.write(filePath);
            return subDir + "/" + uniqueFileName;
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
        String contentDisp = part.getHeader("content-disposition");
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length()-1);
            }
        }
        return "";
    }
    
    
//    public static void downloadFile(HttpServletRequest request, HttpServletResponse response, String filePath, String fileName) throws IOException {
//        // 실제 경로 가져오기
//        String realPath = request.getServletContext().getRealPath(filePath);
//        System.out.println("실제 경로: " + realPath);
//        File file = new File(realPath, fileName);
//
//        // 파일 존재 여부 체크
//        if (!file.exists()) {
//            throw new IOException("파일이 존재하지 않습니다: " + file.getAbsolutePath());
//        }
//
//        // 클라이언트의 브라우저 체크
//        String userAgent = request.getHeader("User-Agent");
//        String encodedFileName;
//        if (userAgent != null && userAgent.indexOf("WOW64") == -1) {
//            encodedFileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
//        } else {
//            encodedFileName = new String(fileName.getBytes("KSC5601"), "ISO-8859-1");
//        }
//
//        // 다운로드할 파일의 응답 헤더 설정
//        response.reset();
//        response.setContentType("application/octet-stream");
//        response.setHeader("Content-Disposition", "attachment; filename=\"" + encodedFileName + "\"");
//        response.setHeader("Content-Length", String.valueOf(file.length()));
//
//        // response 내장 객체를 이용하여 출력 스트림 생성
//        try (FileInputStream inputStream = new FileInputStream(file);
//             OutputStream outputStream = response.getOutputStream()) {
//
//            // 파일 내용을 출력 스트림에 출력
//            byte[] buffer = new byte[4096];
//            int bytesRead;
//            while ((bytesRead = inputStream.read(buffer)) != -1) {
//                outputStream.write(buffer, 0, bytesRead);
//            }
//            outputStream.flush();
//        } catch (FileNotFoundException e) {
//            System.out.println("파일 미확인: " + e.getMessage());
//            e.printStackTrace();
//        } catch (Exception e) {
//            System.out.println("예외 발생: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }

}