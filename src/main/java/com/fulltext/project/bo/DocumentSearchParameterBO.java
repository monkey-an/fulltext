package com.fulltext.project.bo;

import com.fulltext.project.constants.KeyWordTypeEnum;
import lombok.Builder;
import lombok.Data;

/**
 * Description
 * <p>
 * </p>
 * DATE 2020/3/12.
 *
 * @author anlu.
 */

@Data
@Builder
public class DocumentSearchParameterBO{
    private String searchParam;
    private KeyWordTypeEnum keyWordTypeEnum;
}
