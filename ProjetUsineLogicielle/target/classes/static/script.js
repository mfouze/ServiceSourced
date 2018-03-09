$(function() {


    // initiate the decrease command
    $("#debit-btn").on("click", function() {
        var accountToDebit = $("#debit-account-no").val();
        if (accountToDebit === "null" || accountToDebit === undefined) {
            alert("Choisir un diplome dont le volume va varier");
            return;
        }
        var amount = $("#debit-amount").val();
        if (!amount || amount === 0) {
            alert("Veuillez specifier le montant");
            return;
        }

        $.ajax({
            url: "/decrease",
            method: "GET",
            data: {"acc": accountToDebit,"amount":amount},
            success: function(a) {
                $("#debit-amount").val("");
            },
            error: function(a) {
                console.log(a);
            }
        })
    });


    // initiate the credit command
    $("#credit-btn").on("click", function() {
        var accountToCredit = $("#credit-account-no").val();
        if (accountToCredit === "null" || accountToCredit === undefined) {
            alert("Choose Diplome t");
            return;
        }
        var amount = $("#credit-amount").val();
        if (!amount || amount === 0) {
            alert("Specify volume ");
            return;
        }

        $.ajax({
            url: "/increase",
            method: "GET",
            data: {"acc": accountToCredit,"amount":amount},
            success: function(a) {
                $("#credit-amount").val("");
            },
            error: function(a) {
                console.log(a);
            }
        });
    });


    // queries for the view
    setInterval(function() {

        $.ajax({
            url: "/view",
            method: "GET",
            success: function(accounts) {
                var html = "";
                accounts.forEach(function(account){
                    for (var key in account) {
                        html += "<tr><td>" + key + "</td><td>" + account[key] + "</td></tr>"
                    }
                });
                $("table#balance tbody").html(html);
            },
            error: function(a) {
                console.log(a);
            }
        });

    }, 1000);


});