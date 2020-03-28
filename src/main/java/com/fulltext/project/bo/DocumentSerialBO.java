package com.fulltext.project.bo;

import lombok.Builder;
import lombok.Data;

/**
 * Description
 * <p>
 * </p>
 * DATE 2020/3/7.
 *
 * @author anlu.
 */

@Data
@Builder
public class DocumentSerialBO {
    private String documentImagePath;
    private String documentDesc;
    private String documentSummary;
    private String serialName;

    public String getDocumentSummaryDesc(){
        if(documentSummary.length()>48){
            return documentSummary.substring(0,44)+"<abbr title='"+documentSummary.substring(44)+"'>...</abbr>";
        }
        return documentSummary;
    }
}
