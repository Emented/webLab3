function setTimezone() {

    $('[id="form:timezone"]').val(Intl.DateTimeFormat().resolvedOptions().timeZone);

}