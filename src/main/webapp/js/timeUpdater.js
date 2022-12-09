$(function () {
    const UPDATE_DELAY = 5000;

    function setCurrentDateTime() {
        let date = new Date();

        let day = date.getDate();
        let month = date.getMonth() + 1;
        let year = date.getFullYear();

        day = (day < 10) ? '0' + day : day;
        month = (month < 10) ? '0' + month : month;

        let hours = date.getHours();
        let minutes = date.getMinutes();
        let seconds = date.getSeconds();

        hours = (hours < 10) ? '0' + hours : hours;
        minutes = (minutes < 10) ? '0' + minutes : minutes;
        seconds = (seconds < 10) ? '0' + seconds : seconds;

        $('.datetime_date').html(`${day}.${month}.${year}`);
        $('.datetime_time').html(`${hours}:${minutes}:${seconds}`);
    }

    setCurrentDateTime();
    setInterval(setCurrentDateTime, UPDATE_DELAY);
});