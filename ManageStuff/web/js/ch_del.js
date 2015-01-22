
jQuery(document).ready(function() {

    $('body').on('click', '#add', function() {
        var details = $("#divv");
        var request = $.ajax({
            url: "GetJson",
            type: "GET",
            dataType: "json"
        });

        request.done(function(msg) {
            details.empty();
            var dep = "";
            var stuff = "";
            $.each(msg, function(key, value) {
                if (key == "dep") {
                    for (var i = 0; i < value.length; i++) {
                        dep += "<option value='" + (i + 1) + "'>" + value[i] + "</option><br/>";
                    }
                }
                if (key == "stuff") {
                    for (var i = 0; i < value.length; i++) {
                        stuff += "<option value='" + (i + 1) + "'>" + value[i] + "</option><br/>";
                    }
                }

            });
            details.append("\
            <form action='Insert' method='POST'>  \n\
                    Name:     <input type='text' name='full_name' /><br/>\n\
                    UserName: <input type='text' name='username'  /><br/>\n\
                    Password: <input type='password' name='password'  /><br/>\n\
                    Dep Name: <select name=\"dep_name\">" + dep +
                    "</select><br/>\n\
                    Stuff Name: <select name=\"stuff\">" + stuff +
                    "</select><br/>\n\
                    <input type='hidden' name='c_id'  /><br/>\n\
                    <input type='submit' id='save' value='save'> \n\
            </form>");
        });
    });

    $('body').on('click', '#btnsedit', function() {
        var details = $("#divv");
        var c_id = ($(this).attr('data_val'));
        var request = $.ajax({
            url: "GetJson?ids=" + c_id,
            type: "POST",
            dataType: "json"
        });
        request.done(function(msg) {
            details.empty();
            details.append("\
            <form action='Edit' method='POST'>  \n\
                    Name: <input type='text' name='full_name' value=" + msg.week[0] + " /><br/>\n\
                    UserName: <input type='text' name='username' value=" + msg.week[1] + " /><br/>\n\
                    Dep Name: <input type='text' name='dep' value=" + msg.week[2] + " /><br/>\n\
                    <input type='hidden' name='c_id' value=" + c_id + " /><br/>\n\
                    <input type='submit' id='save' value='save'> \n\
            </form>");
        });
    });
    $('body').on('click', '#btnsdelete', function() {
        //var details = $("#h11");
        var c_id = ($(this).attr('data_val'));
        var request = $.ajax({
            url: "Delete?ids=" + c_id,
            type: "POST"
        });
        request.done(function(msg) {
           location.reload();
        });
    });

});