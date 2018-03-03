function getHttpData(url, params,callback) {
    if (typeof params == "object") {
        $.ajax({
            type: "get",
            url: url,
            data: params,
            success: callback
        });
    } else if (params != undefined) {
        $.ajax({
            type: "get",
            url: url + "/" + params,
            success: callback
        });
    } else {
        $.ajax({
            type: "get",
            url: url,
            success: callback
        });
    }
}

function postHttpData(url, params,callback) {
    if (typeof params == "object") {
        console.log(params)
        $.ajax({
            type: "POST",
            url: url,
            contentType: 'application/json',
            data: JSON.stringify(params),
            traditional:true,
            success: callback
        });
    } else if (params != undefined) {
        $.ajax({
            type: "POST",
            url: url + "/" + params,
            success: callback
        });
    } else {
        $.ajax({
            type: "POST",
            url: url,
            success: callback
        });
    }
}

function postFormData(url, params,callback) {
    var formData = new FormData();
    for (var o in params) {
        formData.append(o, params[o]);
    }

    if (typeof params == "object") {
        $.ajax({
            type: "POST",
            url: url,
            data: formData,
            success: callback
        });
    } else if (params != undefined) {
        $.ajax({
            type: "POST",
            url: url + "/" + params,
            success: callback
        });
    } else {
        $.ajax({
            type: "POST",
            url: url,
            success: callback
        });
    }
}