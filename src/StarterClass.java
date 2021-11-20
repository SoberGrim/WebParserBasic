
public class StarterClass {

    static String parse(String text, String from, String to) {
        int linkOffset = text.indexOf(from, 0);
        if (linkOffset<0) return "";
        int linkOffsetEnd = text.indexOf(to, Math.max(linkOffset,0));
        if (linkOffsetEnd<0) linkOffsetEnd = text.length();
        return (linkOffset>=0)&&(linkOffset+from.length()<linkOffsetEnd) ? text.substring(linkOffset + from.length(), linkOffsetEnd) : "";
    };


    static StringBuilder toPrint = new StringBuilder();
    static StringBuilder toPrintRu = new StringBuilder();
    static StringBuilder toPrintUs = new StringBuilder();
    static StringBuilder toPrintEn = new StringBuilder();
    static StringBuilder toPrintCa = new StringBuilder();


    public static void main(String[] args) throws Exception {

        for (int i=122; i<=900; i++) {

            StringBuilder HTMLPage;
            try {
                HTMLPage = GetHTML.getHTML("https://github.com/torvalds?page="+i+"&tab=followers");
            } catch (Exception e) {
                //Thread.sleep(15000);
                HTMLPage = GetHTML.getHTML("https://github.com/torvalds?page="+i+"&tab=followers");
            }

            String user = parse(HTMLPage.toString(), "py-4 border-bottom color-border-muted", "d-table table-fixed col-12 width-full");
            HTMLPage = new StringBuilder(parse(HTMLPage.toString(), "d-table table-fixed col-12 width-full", "\n"));
            HTMLPage = new StringBuilder(parse(HTMLPage.toString(), "d-table table-fixed col-12 width-full", "\n"));
            if (user.equals("")) {
                System.out.println("DONE");
                break;
            };
            while (user != "") {
                String name = parse(user.toString(), "<span class=\"f4 Link--primary\">", "</span>");
                String nickname = parse(user.toString(), "<span class=\"Link--secondary", "</span>");
                nickname = parse(nickname.toString(), ">", "\n");
                String add1 = parse(user.toString(), "</svg>", "\n");
                String add2 = parse(add1.toString(), "</svg> ", "  ");
                add1 = parse(add1.toString(), " ", "  ");

                if (name.length() > 0)
                {
                    //toPrint.append(name + " @" + nickname + " | " + add1 + " | " + add2 + "\n");
                    String adress = add1 + add2;

                    toPrint.append("<tr><th><a href='https://github.com/" + nickname +"'>@"+nickname+"<a>"+"</th><th>"+ name + "</th><th>" + add1 + "</th><th>" + add2 + "</th><th></tr>\n");
                    if ((adress.contains("ussia"))||(adress.contains("uxoft"))||(adress.contains("Volgograd"))||(adress.contains("Perm"))||(adress.contains("Vorone"))||(adress.contains("Krasno"))||(adress.contains("Ufa"))||(adress.contains("Rostov"))||(adress.contains("Omsk"))||(adress.contains("amara"))||(adress.contains("binsk"))||(adress.contains("Kazan"))||(adress.contains("aterinbu"))||(adress.contains("ovosibi"))||(adress.contains("EPAM"))||(adress.contains("oscow"))||(adress.contains("Saint-P"))) toPrintRu.append("<tr><th><a href='https://github.com/" + nickname +"'>@"+nickname+"<a>"+"</th><th>"+ name + "</th><th>" + add1 + "</th><th>" + add2 + "</th><th></tr>\n");
                    if ((adress.contains("USA"))||(adress.contains("usa"))||(adress.contains("gland"))||(adress.contains("anada"))) toPrintEn.append("<tr><th><a href='https://github.com/" + nickname +"'>@"+nickname+"<a>"+"</th><th>"+ name + "</th><th>" + add1 + "</th><th>" + add2 + "</th><th></tr>\n");
                    System.out.println(name + " @" + nickname + " " + add1 + " (" + add2 + ")");
                }

                user = parse(HTMLPage.toString(), "py-4 border-bottom color-border-muted", "d-table table-fixed col-12 width-full");
                HTMLPage = new StringBuilder(parse(HTMLPage.toString(), "d-table table-fixed col-12 width-full", "\n"));
            }
            TextFile.write("user_git_all.html", toPrint.toString());
            TextFile.write("user_git_ru.html", toPrintRu.toString());
            TextFile.write("user_git_us.html", toPrintUs.toString());
            TextFile.write("user_git_ca.html", toPrintCa.toString());
            TextFile.write("user_git_en.html", toPrintEn.toString());
        }
//        for (int user=3000; user>2997; user--){
//            StringBuilder HTMLPage;
//            System.out.println(user);
//            HTMLPage = GetHTML.getHTML("https://platform.kata.academy/api/fake/user/task/1764/mentorCheckTask?studentId=" + user);
//            String git0=parse(HTMLPage.toString(), "\"answer\":\"","\",\"type\":");
//            if (git0.length()==0) continue;
//            git0 = git0.substring(19);
//            git0 = "https://github.com/"+parse(git0, "","/");
//            System.out.println(git0);
//
//            HTMLPage = GetHTML.getHTML("https://platform.kata.academy/api/fake/user/task/959/mentorCheckTask?studentId=" + user);
//            String git=parse(HTMLPage.toString(), "\"answer\":\"","\",\"type\":");
//
//            System.out.println(git);
//
//            HTMLPage = GetHTML.getHTML("https://platform.kata.academy/api/fake/user/name/" + user);
//            String name=parse(HTMLPage.toString(), "data\":\"","\"}");
//            System.out.println(name);
//
//            String postDataStr = "{\"token\":\"xoxc64971f6db8df54172b3\",\"include_profile_only_users\":false,\"count\":50,\"channels\":[\"C2AEE8T9B\"],\"filter\":\"people\",\"index\":\"users_by_display_name\",\"locale\":\"ru-RU\",\"present_first\":false,\"query\":\""+name+"\",\"search_profile_fields\":true}";
//            byte[] postData = postDataStr.getBytes(StandardCharsets.UTF_8);
//
//            HTMLPage = GetHTML.postHTML("https://edgeapi.slack.com/cache/T2AEH901M/users/search", postData);
//            System.out.println(HTMLPage);
//
//            String nickname = parse(HTMLPage.toString(), "\"name\":\"","\",\"deleted\"");
//            String tz = parse(HTMLPage.toString(), "\"tz_label\":\""," Time");
//            String phone = parse(HTMLPage.toString(), "\"phone\":\"","\",\"skype\"");
//            String skype = parse(HTMLPage.toString(), "\"skype\":\"","\",\"");
//            String avatar = parse(HTMLPage.toString(), "image_original\":\"","\",\"");
//            avatar = avatar.replaceAll("[\\\\]", "");
//            String email = parse(HTMLPage.toString(), "\"email\":\"","\",\"");
//
//            System.out.println(nickname + " " + tz + " " + phone + " " + skype + " " + avatar + " " + email);
//
//            if (git.length()>0) {
//                toPrint.append("<tr><th><img style=\"max-width:50px;max-height:50px;\" src="+avatar+"></th><th><a href='https://platform.com/fake/user/" + user +"/statistic'>"+user+"<a>"+"</th><th>"+ name + "</th><th>" + nickname + "</th><th>" + tz + "</th><th><a href=\"mailto:"+email+"\">" +email + "</a></th><th>" + phone + "</th><th>"+skype+ "</th><th><a href='" + git0+ "'>"+git0.substring(8)+"<a></th><th><a href='" + git+"'>(313)<a></th></tr>\n");
//            } else {
//                toPrint.append("<tr><th><img style=\"max-width:50px;max-height:50px;\" src="+avatar+"></th><th><a href='https://platform.com/fake/user/" + user +"/statistic'>"+user+"<a>"+"</th><th>"+ name + "</th><th>" + nickname + "</th><th>" + tz + "</th><th><a href=\"mailto:"+email+"\">" +email + "</a></th><th>" + phone + "</th><th>"+skype+ "</th><th><a href='" + git0+ "'>"+git0.substring(8)+"<a></th><th>(none)</th></tr>\n");
//            }


        //TextFile.write("user4000.html", toPrint.toString());
    }
}