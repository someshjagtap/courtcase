export interface IUserData {
  id?: number;
  firstname?: string | null;
  lastName?: string | null;
  contactNo?: string | null;
  emailId?: string | null;
  address?: string | null;
  userName?: string | null;
  password?: string | null;
  verifyPassword?: string | null;
  lastModifiedBy?: string | null;
  lastModified?: string | null;
}

export class UserData implements IUserData {
  constructor(
    public id?: number,
    public firstname?: string | null,
    public lastName?: string | null,
    public contactNo?: string | null,
    public emailId?: string | null,
    public address?: string | null,
    public userName?: string | null,
    public password?: string | null,
    public verifyPassword?: string | null,
    public lastModifiedBy?: string | null,
    public lastModified?: string | null
  ) {}
}

export function getUserDataIdentifier(userData: IUserData): number | undefined {
  return userData.id;
}
