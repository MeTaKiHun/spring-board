
package com.board.shop.DTO;

import com.board.shop.Entity.BoardEntity;
import com.board.shop.Entity.FileEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileDTO {

        private Long fileidx;

        private BoardEntity board;

        private String filepath;

        private String uploadname;

        private String realfilename;

        private double filesize;

        private Date filedate;

        public FileEntity toEntity(BoardEntity boardEntity){

            return  FileEntity.builder()
                    .fileIdx(fileidx)
                    .filepath(filepath)
                    .board(boardEntity)
                    .uploadname(uploadname)
                    .realfilename(realfilename)
                    .filesize(filesize)
                    .build();
        }
    }
