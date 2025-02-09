document.addEventListener("scroll", () => {
  const header = document.querySelector("body > header");
  if (window.scrollY > 20) {
    header.classList.add("scrolled");
  } else {
    header.classList.remove("scrolled");
  }
});
