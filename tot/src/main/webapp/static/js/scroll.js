document.addEventListener('DOMContentLoaded', () => {
    const scrollContainer = document.getElementById('scrollContainer');
    const contentContainer = document.getElementById('contentContainer');
    const totalHeight = scrollContainer.scrollHeight - 200; // 전체 높이에서 보이는 영역 제외
    scrollContainer.addEventListener('scroll', () => {
        const scrollY = scrollContainer.scrollTop; // 현재 스크롤 위치
        const translateX = (scrollY / totalHeight) * (contentContainer.scrollWidth - 600); // 가로 이동 거리 계산
        contentContainer.style.transform = `translateX(-${translateX}px)`; // 가로 이동 적용
    });

});