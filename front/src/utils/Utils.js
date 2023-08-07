export function getRemainingDays(rcptenddt) {
  if (!rcptenddt) {
    return "---";
  }
  const targetDate = new Date(rcptenddt);
  const currentDate = new Date();
  const timeDiff = targetDate - currentDate;
  const remainingDays = Math.ceil(timeDiff / (1000 * 3600 * 24));

  if (remainingDays < 0) return "---";
  if (remainingDays === 0) return "day";
  return remainingDays;
}

export function decodeHTMLEntities(text) {
  const textarea = document.createElement("textarea");
  textarea.innerHTML = text;
  return textarea.value;
}
