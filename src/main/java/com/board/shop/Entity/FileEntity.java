
package com.board.shop.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Table(name = "a_board_file")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@EntityListeners(AuditingEntityListener.class)
@Entity
public class FileEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "a_board_fileseq")
        @SequenceGenerator(name = "a_board_fileseq", sequenceName = "a_board_fileseq", allocationSize = 1)
        @Column(name = "file_idx")
        private Long fileIdx;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "board_idx")
        private BoardEntity board;

        @Column(name = "filepath")
        private String filepath;

        @Column(name = "uploadname")
        private String uploadname;

        @Column(name = "realfilename")
        private String realfilename;

        @Column(name = "filesize")
        private double filesize;

}

