package com.example.searchteam.dto.request.util;

import com.example.searchteam.util.Pair;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.io.File;
import java.util.Collections;
import java.util.List;

@Data
@Accessors(chain = true)
public class EmailSendRequest {

    private String text;
    private String subject;

    private List<String> to = Collections.emptyList();
    private List<String> cc = Collections.emptyList();
    private List<String> bcc = Collections.emptyList();

    private List<Pair<String, File>> files = Collections.emptyList();

}
