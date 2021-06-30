package rs.ac.uns.ftn.devops.tim5.nistagrammedia.service;

import org.springframework.web.multipart.MultipartFile;
import rs.ac.uns.ftn.devops.tim5.nistagrammedia.exception.ResourceNotFoundException;
import rs.ac.uns.ftn.devops.tim5.nistagrammedia.model.Media;

import java.io.File;
import java.io.IOException;

public interface MediaService {
    File getMedia(Long id) throws ResourceNotFoundException;

    Media upload(MultipartFile file) throws IOException;
}
