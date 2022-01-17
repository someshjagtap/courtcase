import dayjs from 'dayjs/esm';
import { ICourtCase } from 'app/entities/court-case/court-case.model';

export interface IHearing {
  id?: number;
  hearingDate?: dayjs.Dayjs | null;
  nextHearingDate?: dayjs.Dayjs | null;
  description?: string | null;
  previousHearingDate?: dayjs.Dayjs | null;
  conclusion?: string | null;
  comment?: string | null;
  status?: string | null;
  freefield1?: string | null;
  freefield2?: string | null;
  freefield3?: string | null;
  freefield4?: string | null;
  freefield5?: string | null;
  lastModifiedBy?: string | null;
  lastModified?: string | null;
  hearing?: ICourtCase | null;
}

export class Hearing implements IHearing {
  constructor(
    public id?: number,
    public hearingDate?: dayjs.Dayjs | null,
    public nextHearingDate?: dayjs.Dayjs | null,
    public description?: string | null,
    public previousHearingDate?: dayjs.Dayjs | null,
    public conclusion?: string | null,
    public comment?: string | null,
    public status?: string | null,
    public freefield1?: string | null,
    public freefield2?: string | null,
    public freefield3?: string | null,
    public freefield4?: string | null,
    public freefield5?: string | null,
    public lastModifiedBy?: string | null,
    public lastModified?: string | null,
    public hearing?: ICourtCase | null
  ) {}
}

export function getHearingIdentifier(hearing: IHearing): number | undefined {
  return hearing.id;
}
