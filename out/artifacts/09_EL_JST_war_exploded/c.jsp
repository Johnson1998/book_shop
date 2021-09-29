<%@ page import="com.john.Person" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="javax.lang.model.element.NestingKind" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %><%--
  Created by IntelliJ IDEA.
  User: John
  Date: 2021/9/20
  Time: 19:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        Person person = new Person();
        person.setName("华仔");
        person.setId(123);
        ArrayList<String> cities = new ArrayList<>();
        cities.add("北京");
        cities.add("上海");
        cities.add("广东");
        person.setCities(cities);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        person.setMap(map);

        pageContext.setAttribute("p", person);
    %>

    输出Person ${ p } <br>
    输出person的name属性：${ p.name } <br>
    输出第二个城市： ${ p.cities[0]} <br>
    输出第二个key2值: ${ p.map.key2 } <br>
</body>
</html>
