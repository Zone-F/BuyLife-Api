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

export class CreateTaskDTO {
  @IsNotEmpty()
  userId: number;

  @IsString()
  @IsNotEmpty()
  title: string;

  @IsString()
  description: string;

  @IsDate()
  startDate: Date;

  @IsEnum(RepeatCycle)
  repeatCycle: RepeatCycle;
}

export class CheckInDTO {
  @IsNumber()
  @IsNotEmpty()
  taskId: number; // 打卡任务ID
}

export class UndoCheckInDTO {
  @IsInt()
  readonly checkInId: number;
}

export class TaskDTO {
  @IsString()
  @IsNotEmpty()
  readonly taskName: string;

  @IsString()
  @IsNotEmpty()
  readonly description: string;

  @IsInt()
  @IsOptional()
  readonly userId?: number; // 你可以根据需要决定是否每个任务都要绑定到特定用户
}
