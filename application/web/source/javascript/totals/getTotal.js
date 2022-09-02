export default function getTotal() {
  const urlParams = new URLSearchParams(window.location.search);
  return urlParams.get("total_amount") == null
    ? 0
    : parseInt(urlParams.get("total_amount"));
}
