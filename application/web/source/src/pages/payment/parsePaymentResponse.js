export default function parsePaymentResponse(data) {
  return (
    data.hasOwnProperty("status") && data.status == "succeeded" && data.paid
  );
}
