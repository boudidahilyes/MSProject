export interface Cart {
  id?: number;
  userId: string;
  productId: number;
  quantity: number;
  added_at?: string; // ISO string format for LocalDateTime
}