document.querySelector(".side--bar--icon").addEventListener("click", (e) => {
  console.log(e.target);
    e.target.classList.toggle("menu-on");
    document.querySelector(".side--bar").classList.toggle("menu-on");
});