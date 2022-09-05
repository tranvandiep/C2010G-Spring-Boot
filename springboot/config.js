const BASE_URL = "http://localhost:8080/"
const API_CATEGORY_LIST = BASE_URL + "api/category/list"
const API_CATEGORY_ADD = BASE_URL + "api/category/add"
const API_CATEGORY_EDIT = BASE_URL + "api/category/update"
const API_CATEGORY_DELETE = BASE_URL + "api/category/delete"
const API_CATEGORY_FIND = BASE_URL + "api/category/find/"


function getParameterByName(name, url) {
    if (!url) url = window.location.href;
    name = name.replace(/[\[\]]/g, '\\$&');
    var regex = new RegExp('[?&]' + name + '(=([^&#]*)|&|#|$)'),
        results = regex.exec(url);
    if (!results) return null;
    if (!results[2]) return '';
    return decodeURIComponent(results[2].replace(/\+/g, ' '));
}
