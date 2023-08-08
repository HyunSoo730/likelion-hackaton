export function getRemainingDays(endDate) {
  if (!endDate) {
    return "---";
  }
  const end = new Date(endDate);
  const now = new Date();
  const timeDiff = end - now;
  const daysDiff = Math.ceil(timeDiff / (1000 * 3600 * 24));

  if (daysDiff < 0) return "end";
  if (daysDiff === 0) return "day";
  return daysDiff;
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
  if (daysDiff === 0) return "day";
  return daysDiff >= 0 ? `${daysDiff}` : "end";
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
  return `${year}-${month}-${day}`;
};
