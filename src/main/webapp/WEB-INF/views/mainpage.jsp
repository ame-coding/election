
<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<script src="/jquery.min.js"></script>
<title>Login</title>
<style>

</style>
</head>
<body>

  <p id="check"></p>


<script>
const base = '<%= request.getContextPath() %>/';

$(document).ready(function () {


        $.ajax({
            url: base + "word",
            method: "GET",
			dataType: "json",

            success: function (response) {
             
                const message = response.message;

                    $("#check").text(message);
            },

            error: function () {
                 $("#check").text("Error contacting server.");
            }
        });
  
});
</script>




</body>
</html>