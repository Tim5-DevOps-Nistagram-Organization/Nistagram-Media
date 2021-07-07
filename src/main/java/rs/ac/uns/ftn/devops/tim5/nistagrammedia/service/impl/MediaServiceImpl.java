package rs.ac.uns.ftn.devops.tim5.nistagrammedia.service.impl;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import rs.ac.uns.ftn.devops.tim5.nistagrammedia.exception.ResourceNotFoundException;
import rs.ac.uns.ftn.devops.tim5.nistagrammedia.model.Media;
import rs.ac.uns.ftn.devops.tim5.nistagrammedia.repository.MediaRepository;
import rs.ac.uns.ftn.devops.tim5.nistagrammedia.service.MediaService;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class MediaServiceImpl implements MediaService {

    @Value("${media.storage.folder.path}")
    private String mediaStoragePath;

    private final MediaRepository mediaRepository;

    @Autowired
    public MediaServiceImpl(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }

    @Override
    public File getMedia(Long id) throws ResourceNotFoundException {
        Media media = mediaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Media"));
        return new File(media.getPath());
    }

    @Override
    public Media upload(MultipartFile multipartFile) throws IOException {

        String userDirectory = System.getProperty("user.dir");
        userDirectory += mediaStoragePath;
        System.out.println(userDirectory);
        File file = new File(userDirectory + File.separator + UUID.randomUUID());
        FileUtils.writeByteArrayToFile(file, IOUtils.toByteArray(multipartFile.getInputStream()));
        Media media = new Media(file.getPath());
        return mediaRepository.save(media);
    }

}
