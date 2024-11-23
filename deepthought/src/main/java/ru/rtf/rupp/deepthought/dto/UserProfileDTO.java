package ru.rtf.rupp.deepthought.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserProfileDTO {
    private String firstName;

    private String lastName;

    private String middleName;

    private String about;

    private String status;

    private String avatar;
}
