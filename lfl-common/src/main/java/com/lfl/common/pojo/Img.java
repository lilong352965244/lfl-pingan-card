package com.lfl.common.pojo;


import lombok.Data;

import java.io.Serializable;

@Data
public class Img implements Serializable {
    private static final long serialVersionUID = 2038260097379227515L;

    private String filedir;
    private String suffix;

}
