package com.davimc.cursomc.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class URL {

    public static String decodeParam(String s) {
        try {
            return URLDecoder.decode(s,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    public static List<Long> decodeLongList(String s){
        /*String[] vet = s.split(",");
        List<Long> ids = new ArrayList<>();
        for(String i : vet)
            ids.add(Long.getLong(i));
        return ids;*/
        System.out.println(s+ "\n\n\n");
        return s.equals("")
                ? new ArrayList<Long>()
                : Arrays.asList(s.split(",")).stream()
                    .map(x -> Long.valueOf(x).longValue())
                    .collect(Collectors.toList());
    }
}
