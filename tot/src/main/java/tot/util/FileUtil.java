package tot.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUtil {

    @Value("${file.upload-dir}")
    private String uploadDir;

    public String saveImage(MultipartFile file) {
        if (uploadDir == null || uploadDir.isEmpty()) {
            throw new IllegalStateException("파일 업로드 디렉토리 설정 확인이 필요합니다.");
        }

        File uploadDirFile = new File(uploadDir);
        if (!uploadDirFile.exists()) {
            uploadDirFile.mkdirs();
        }

        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null) {
            throw new IllegalArgumentException("Invalid file: 파일 이름이 null입니다.");
        }

        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String filename = UUID.randomUUID().toString() + extension;
        Path targetPath = Paths.get(uploadDir, filename);

        try {
            Files.copy(file.getInputStream(), targetPath);
            targetPath.toFile().setLastModified(System.currentTimeMillis());
            return "/upload/" + filename;
        } catch (IOException e) {
            throw new RuntimeException("파일 저장 중 오류가 발생했습니다.", e);
        }
    }
    
}
