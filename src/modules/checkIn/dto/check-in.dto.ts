// src/dto/checkIn.dto.ts
import { RepeatCycle } from '@/common/enums/repeatCycle.enum';
import {
  IsNotEmpty,
  IsInt,
  IsString,
  IsOptional,
  IsNumber,
  IsDate,
  IsEnum,
} from 'class-validator';

export class CheckInDTO {
  @IsNumber()
  @IsNotEmpty()
  taskId: number; // 打卡任务ID
}

export class UndoCheckInDTO {
  @IsInt()
  readonly checkInId: number;
}
