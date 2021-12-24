import * as dayjs from 'dayjs';
import { IUserData } from 'app/entities/user-data/user-data.model';
import { IHearing } from 'app/entities/hearing/hearing.model';

export interface ICourtCase {
  id?: number;
  caseNo?: string | null;
  villageName?: string | null;
  accuserName?: string | null;
  applicationNo?: string | null;
  landReferenceNo?: string | null;
  firstAppeal?: string | null;
  amount?: string | null;
  projectName?: string | null;
  courtName?: string | null;
  defendantName?: string | null;
  caseDescription?: string | null;
  caseFilingDate?: dayjs.Dayjs | null;
  totalClaimAmount?: string | null;
  caseOfficer?: string | null;
  caselawyer?: string | null;
  nextHearingDate?: dayjs.Dayjs | null;
  amountDepositeInCourt?: string | null;
  lar?: string | null;
  incCompensation?: string | null;
  amountPaidSLO?: string | null;
  chequeNo?: string | null;
  chequeDate?: dayjs.Dayjs | null;
  appealNo?: string | null;
  courtAmount?: string | null;
  appealAmount?: string | null;
  appealDate?: dayjs.Dayjs | null;
  description?: string | null;
  comment?: string | null;
  caseStatus?: string | null;
  freefield1?: string | null;
  freefield2?: string | null;
  freefield3?: string | null;
  lastModifiedBy?: string | null;
  lastModified?: string | null;
  users?: IUserData[] | null;
  hearing?: IHearing | null;
}

export class CourtCase implements ICourtCase {
  constructor(
    public id?: number,
    public caseNo?: string | null,
    public villageName?: string | null,
    public accuserName?: string | null,
    public applicationNo?: string | null,
    public landReferenceNo?: string | null,
    public firstAppeal?: string | null,
    public amount?: string | null,
    public projectName?: string | null,
    public courtName?: string | null,
    public defendantName?: string | null,
    public caseDescription?: string | null,
    public caseFilingDate?: dayjs.Dayjs | null,
    public totalClaimAmount?: string | null,
    public caseOfficer?: string | null,
    public caselawyer?: string | null,
    public nextHearingDate?: dayjs.Dayjs | null,
    public amountDepositeInCourt?: string | null,
    public lar?: string | null,
    public incCompensation?: string | null,
    public amountPaidSLO?: string | null,
    public chequeNo?: string | null,
    public chequeDate?: dayjs.Dayjs | null,
    public appealNo?: string | null,
    public courtAmount?: string | null,
    public appealAmount?: string | null,
    public appealDate?: dayjs.Dayjs | null,
    public description?: string | null,
    public comment?: string | null,
    public caseStatus?: string | null,
    public freefield1?: string | null,
    public freefield2?: string | null,
    public freefield3?: string | null,
    public lastModifiedBy?: string | null,
    public lastModified?: string | null,
    public users?: IUserData[] | null,
    public hearing?: IHearing | null
  ) {}
}

export function getCourtCaseIdentifier(courtCase: ICourtCase): number | undefined {
  return courtCase.id;
}
