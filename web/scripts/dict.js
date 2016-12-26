/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

"use strict";
var start = function () {

    var callajax = function () {
        $("#newText").empty();
        $("#validate1").removeClass("show");
        $("#validate2").removeClass("show");
        var input = $("#input").val();
        if (input === "") {
            $("#validate1").addClass("show");
        } else {
            $("#validate1").removeClass("show");
            $.ajax("http://localhost:8080/OnlineDictionary/DictionaryServlet", {
                "type": "post",
                dataType: 'json',
                "data": {
                    "text": $("#input").val()
                }
            })
                    .done(function (data) {

                        if (data.length > 0) {
                            $("#newText").empty();
                            $.each(data, function (i, item) {
                                var li = $("<li><a></a></li>");
                                $("#newText").append(li);
                                if (item.wordtype === "") {
                                    $(li).text(item.definition);

                                } else {
                                    $(li).text("(" + item.wordtype + ") :: " + item.definition);
                                }
                            });
                        } else
                        {
                            $("#validate2").addClass("show");

                        }

                    })
                    .fail(function (errMsg) {
                        alert(errMsg);

                    });
        }
        ;
    };
    return {
        init: function () {
            callajax();

        }
    };
}();
$(document).ready(function () {

    $("#submitBtn").click(start.init);
    $("#input").keyup(function (event) {
        if (event.keyCode === 13) {
            $("#submitBtn").click();
        }
    });

});




 