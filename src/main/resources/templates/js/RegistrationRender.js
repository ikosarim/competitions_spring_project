$(document).ready(function () {
    choiceRoleListener();
});

function choiceRoleListener() {
    var roleName;
    $("#choice_role").on('click', function () {
        roleName = encodeURIComponent($("#choose_role")).val();
        if (roleName !== 'Guest') {
            $("#common_data").show();
            $("#passport_data").show();
            $("#phones").show();
            $("#sendData").show();
            if (roleName === 'Member') {
                $("#member_special_data").show();
            } else if (roleName === 'Captain') {
                $("#captain_special_data").show();
            } else if (roleName === 'Lead') {
                $("#lead_special_data").show();
            } else {
                $("#user_role").hide();
            }
        }
    });
}