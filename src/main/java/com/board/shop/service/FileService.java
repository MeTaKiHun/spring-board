package com.board.shop.service;

import com.board.shop.DTO.FileDTO;
import com.board.shop.Entity.BoardEntity;
import com.board.shop.Entity.FileEntity;
import com.board.shop.Repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {
   private final FileRepository fileRepository;

   public boolean fileDelete(Long fileidx){
       FileEntity fileEntity = fileRepository.findByFileIdx(fileidx);
       String filepath= fileEntity.getFilepath();
       File file = new File(filepath);
       if(file.exists()){
           fileRepository.delete(fileEntity);
           return file.delete();
       }else {
           return false;
       }
   }
   public void fileSave(FileDTO fdto, List<MultipartFile> files, BoardEntity bEntity)throws Exception{
       LocalDate currentDate = LocalDate.now();
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
       String formattedDate = currentDate.format(formatter);
       String filepath =  "D:/shop/file/"+formattedDate+"/"+bEntity.getTitle()+"/";
       for (int i = 0; i < files.size(); i++) {
           MultipartFile file = files.get(i);
           UUID uuid = UUID.randomUUID();
           String original = file.getOriginalFilename();
           String filename = uuid + "_" + original;
           String path = formattedDate+"/"+bEntity.getTitle()+"/" + filename;
           if (file.getSize() > 0) {
               fdto.setRealfilename(original);
               fdto.setUploadname(filename);
               fdto.setFilepath(path);
               fdto.setFilesize(file.getSize());
               File file1 = new File(filepath +filename );
               file1.mkdirs();
               file.transferTo(file1);
               fileRepository.save(fdto.toEntity(bEntity));
           }
       }
   }
   public List<FileEntity> fileGallery(){
       /* return fileRepository.findFilepath();*/
       return fileRepository.findAll(Sort.by(Sort.Direction.DESC, "fileIdx"));
   }
}

