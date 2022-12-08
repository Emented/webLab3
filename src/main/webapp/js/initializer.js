function init() {

    setTimezone();
    $('[id="form:y_value"]').on('input', limitLength);

}

