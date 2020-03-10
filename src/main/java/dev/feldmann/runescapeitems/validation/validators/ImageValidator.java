package dev.feldmann.runescapeitems.validation.validators;

import dev.feldmann.runescapeitems.validation.annotations.ValidImage;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class ImageValidator implements ConstraintValidator<ValidImage, MultipartFile> {

    private ValidImage annotation;

    private List<String> allowedMimeTypes = Arrays.asList(
            "image/png",
            "image/jpg",
            "image/jpeg"
    );

    @Override
    public void initialize(ValidImage constraintAnnotation) {
        this.annotation = constraintAnnotation;
    }

    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
        if (value == null) return false;
        String contentType = value.getContentType();
        if (!allowedMimeTypes.contains(contentType)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Only PNG or JPG images are allowed")
                    .addConstraintViolation();
            return false;
        }
        double wantedRatio = annotation.ratio();
        if (wantedRatio > 0) {
            try (InputStream inputStream = value.getInputStream()){
                // Ta dando erro aqui, pois na hora que vai escrever a imagem a stream já foi utilizada aqui
                // pra fazer isto aqui direito é preciso copiar a imagem pra outra localização temporaria pois ao
                // fechar a stream o spring deleta automaticamente
                BufferedImage image = ImageIO.read(inputStream);
                if (image != null) {
                    double ratio = (double) image.getWidth() / (double) image.getHeight();
                    if (ratio != wantedRatio) {
                        context.disableDefaultConstraintViolation();
                        context.buildConstraintViolationWithTemplate("The image ratio must be " + wantedRatio)
                                .addConstraintViolation();
                        return false;
                    }
                }
            } catch (IOException e) {
                return false;
            }
        }
        return true;
    }

}
