import {
  IsDate,
  IsEnum,
  IsInt,
  IsNotEmpty,
  IsNumber,
  IsString,
  Min,
} from 'class-validator';
import { Type } from 'class-transformer';
import { RepeatCycle } from '@/common/enums/repeatCycle.enum';

export class DailyTaskDto {
  @IsNotEmpty()
  @IsInt()
  taskId: number;

  @IsNotEmpty()
  @IsInt()
  userId: number;

  @IsNotEmpty()
  @Min(0)
  status: number;

  @IsNotEmpty()
  @IsString()
  checkInTime: string;

  @IsNotEmpty()
  @Type(() => Date)
  expiryTime: Date;
}

export class TaskDTO {
  @IsNotEmpty()
  @IsNumber()
  userId: number;

  @IsNotEmpty()
  @IsString()
  title: string;

  @IsNotEmpty()
  @IsString()
  description: string;

  @IsNotEmpty()
  @IsString()
  startDate: string;

  @IsNotEmpty()
  @IsEnum(RepeatCycle)
  repeatCycle: RepeatCycle;
}

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
