function nextPage() {
    const nextPageUrl = `${window.location.pathname}?page=${currentPage+1}&keyword=${keywordValue}&category=${categoryValue}`
    window.location.href = nextPageUrl;
}

function prevPage() {
    const prevPageUrl = `${window.location.pathname}?page=${currentPage-1}&keyword=${keywordValue}&category=${categoryValue}`
    window.location.href = prevPageUrl;
}

function goToPage(pageNum) {

    const PageUrl = `${window.location.pathname}?page=${pageNum}&keyword=${keywordValue}&category=${categoryValue}`;
    window.location.href = PageUrl;
}