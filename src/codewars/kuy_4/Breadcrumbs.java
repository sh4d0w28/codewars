package codewars.kuy_4;

import java.net.URI;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * As breadcrumb menùs are quite popular today, I won't digress much on explaining them,
 * leaving the wiki link to do all the dirty work in my place.

 What might not be so trivial is instead to get a decent breadcrumb from your current url.
 For this kata, your purpose is to create a function that takes a url, strips the first part (labelling it always HOME)
 and then builds it making each element but the last a <a> element linking to the relevant path;
 last has to be a <span> element getting the active class.

 All elements need to be turned to uppercase and separated by a separator, given as the second parameter of the function;
 the last element can terminate in some common extension like .html, .htm, .php or .asp; if the name of the last element is index.something, you treat it as if it wasn't there, sending users automatically to the upper level folder.

 A few examples can be more helpful than thousands of words of explanation, so here you have them:

 Solution.generate_bc("mysite.com/pictures/holidays.html", " : ").equals( '<a href="/">HOME</a> : <a href="/pictures/">PICTURES</a> : <span class="active">HOLIDAYS</span>' );
 Solution.generate_bc("www.codewars.com/users/GiacomoSorbi", " / ").equals( '<a href="/">HOME</a> / <a href="/users/">USERS</a> / <span class="active">GIACOMOSORBI</span>' );
 Solution.generate_bc("www.microsoft.com/docs/index.htm", " * ").equals( '<a href="/">HOME</a> * <span class="active">DOCS</span>' );
 Seems easy enough?

 Well, probably not so much, but we have one last extra rule:
 if one element (other than the root/home) is longer than 30 characters, you have to shorten it, acronymizing it
 (i.e.: taking just the initials of every word);
 url will be always given in the format this-is-an-element-of-the-url and you should ignore words in this array while
 acronymizing: ["the","of","in","from","by","with","and", "or", "for", "to", "at", "a"];
 a url composed of more words separated by - and equal or less than 30 characters long needs to be just
 uppercased with hyphens replaced by spaces.

 Ignore anchors (www.url.com#lameAnchorExample) and parameters (www.url.com?codewars=rocks&pippi=rocksToo) when present.

 Examples:

 Solution.generate_bc("mysite.com/very-long-url-to-make-a-silly-yet-meaningful-example/example.htm", " > ").equals( '<a href="/">HOME</a> > <a href="/very-long-url-to-make-a-silly-yet-meaningful-example/">VLUMSYME</a> > <span class="active">EXAMPLE</span>' );

 Solution.generate_bc("www.very-long-site_name-to-make-a-silly-yet-meaningful-example.com/users/giacomo-sorbi", " + ").equals( '<a href="/">HOME</a> + <a href="/users/">USERS</a> + <span class="active">GIACOMO SORBI</span>' );
 You will always be provided valid url to webpages in common formats, so you probably shouldn't bother validating them.
 *
 */
public class Breadcrumbs {

    private static List<String> stopWords = Arrays.asList("THE","OF","IN","FROM","BY","WITH","AND", "OR", "FOR", "TO", "AT", "A");

    public static String generate_bc(String url, String separator) {

        Map<String, String> pathParts = getParts(url);

        if (pathParts.size() == 1) {
            return "<span class=\"active\">HOME</span>";
        }
        List<String> sb = new ArrayList<>();
        for (String key: pathParts.keySet()) {

            String part = key;
            String title = pathParts.get(key);

            String linkTitle;
            String linkPart;
            if(part.equals("/")) {
                linkPart = "/";
                linkTitle = "HOME";
            } else {
                linkPart = part;
                linkTitle = title.toUpperCase();
            }

            int iofdot = linkTitle.indexOf(".");
            if (iofdot > 0) {
                linkTitle = linkTitle.substring(0, iofdot);
            }

            linkTitle = postProcess(linkTitle);

            if(!linkPart.equals("")) {
                part = String.format("<a href=\"%s\">%s</a>", linkPart, linkTitle);
            } else {
                part = String.format("<span class=\"active\">%s</span>", linkTitle);
            }
            sb.add(part);
        }

        return String.join(separator, sb);
    }

    private static String postProcess(String s) {
        if(s.length() > 30) {
            String[] linkTitleParts = s.split("-");
            StringBuilder sb1 = new StringBuilder();
            for(String linkTitlePart: linkTitleParts) {
                if(stopWords.contains(linkTitlePart)) {
                    continue;
                }
                sb1.append(linkTitlePart.substring(0,1));
            }
            s = sb1.toString();
        }
        return s.replaceAll("-", " ");
    }

    private static Map<String, String> getParts(String url) {

        if(!url.startsWith("http")) {
            if (!url.startsWith("www.")) {
                url = "www." + url;
            }
            if (!url.startsWith("http")) {
                url = "http://" + url;
            }
        }

        URI u = URI.create(url);

        Map<String, String> allMatches = new LinkedHashMap<>();
        Pattern p = Pattern.compile("/");
        Matcher m = p.matcher(u.getPath());
        int e = 0;
        boolean found = false;
        while (m.find()) {
            allMatches.put(
                    u.getPath().substring(0,m.end()), // link
                    u.getPath().substring(e,m.end()-1) // title
            );
            e = m.end();
            found = true;
        }
        if(!found) {
            allMatches.put("", "");
            return allMatches;
        }

        if(u.getPath().substring(e).toUpperCase().startsWith("INDEX") || u.getPath().substring(e).toUpperCase().isEmpty()) {
            Object[] allMatchesKeys = allMatches.keySet().toArray();
            String lastKey = allMatchesKeys[allMatchesKeys.length-1].toString();
            String lastVal = allMatches.get(lastKey).toString();
            allMatches.remove(lastKey);
            allMatches.put("", lastVal);
        } else {
            allMatches.put("", u.getPath().substring(e).replaceAll("-", " "));
        }
        return allMatches;
    }

    public static void main(String[] args) {

        System.out.println(generate_bc("www.agcpartners.co.uk", " > "));
        System.out.println(generate_bc("www.codewars.com/users/GiacomoSorbi?ref=CodeWars", " > "));
        System.out.println(generate_bc("www.microsoft.com/docs/index.htm#top", " : "));
        System.out.println(generate_bc("mysite.com/very-long-url-to-make-a-silly-yet-meaningful-example/example.asp", ","));
        System.out.println(generate_bc("https://www.linkedin.com/in/giacomosorbi", "."));
    }
}

