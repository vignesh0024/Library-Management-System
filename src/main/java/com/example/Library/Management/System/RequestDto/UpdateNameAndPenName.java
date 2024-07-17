package com.example.Library.Management.System.RequestDto;

import com.example.Library.Management.System.Model.Author;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateNameAndPenName {
    public Integer authorId;
    public String newName;
    public String newPenName;

}
