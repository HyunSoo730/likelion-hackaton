export function getRemainingDays(rcptenddt) {
  if (!rcptenddt) {
    return "---";
  }
  const targetDate = new Date(rcptenddt);
  const currentDate = new Date();
  const timeDiff = targetDate - currentDate;
  const remainingDays = Math.ceil(timeDiff / (1000 * 3600 * 24));

  if (remainingDays < 0) return "마감";
  if (remainingDays === 0) return "day";
  return remainingDays;
}

export const getRemainingDays2 = (endDate) => {
  const now = new Date();
  const end = new Date(
    endDate.slice(0, 4),
    endDate.slice(4, 6) - 1,
    endDate.slice(6, 8)
  );
  const timeDiff = end - now;
  const daysDiff = Math.ceil(timeDiff / (1000 * 60 * 60 * 24));
  return daysDiff >= 0 ? `${daysDiff}` : "마감";
};

export function decodeHTMLEntities(text) {
  const textarea = document.createElement("textarea");
  textarea.innerHTML = text;
  return textarea.value;
}

export const formatDate = (dateString) => {
  const year = dateString.slice(0, 4);
  const month = dateString.slice(4, 6);
  const day = dateString.slice(6, 8);
  return `${year}/${month}/${day}`;
};
