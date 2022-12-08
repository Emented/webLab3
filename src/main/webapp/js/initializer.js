function init() {

    setTimezone();
    setInitialRadius();
    $('[id="form:y_value"]').on('input', limitLength);

}

