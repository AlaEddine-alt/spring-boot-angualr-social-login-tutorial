export interface User {
  id?: string;
  email: string;
  firstName: string;
  lastName: string;
  phoneNumber: string;
  profilePictureUrl: string;
  emailVerified: boolean;
  enabled: boolean;
  createdAt: Date;
  updatedAt: Date;
  provider: string;
  providerId: string;
}
