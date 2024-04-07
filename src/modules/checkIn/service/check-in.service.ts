import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { CheckIn, CheckInTask } from '@/entities/checkIn.entity'; // 假设你已经创建了CheckIn实体
// import { CheckInDTO } from '@/modules/checkIn/dto/check-in.dto';
// import { RepeatCycle } from '@/common/enums/repeatCycle.enum';
// import { CreateTaskDTO } from '../dto/check-in-task.dto';

@Injectable()
export class CheckInService {
  constructor(
    @InjectRepository(CheckIn)
    private readonly checkInRepository: Repository<CheckIn>,
    @InjectRepository(CheckInTask)
    private readonly taskRepository: Repository<CheckInTask>,
  ) {}
  // async getCheckInsForUser(userId: number): Promise<CheckIn[]> {
  //   // 从数据库中查找并返回用户的所有打卡记录
  //   return this.checkInRepository.find({ where: { userId: userId } });
  // }

  // async undoCheckIn(checkInId: number): Promise<void> {
  //   // 找到对应的打卡记录并删除它
  //   await this.checkInRepository.delete(checkInId);
  // }

  // async getCheckInTasksForUser(userId: number): Promise<any[]> {
  //   // 返回用户打卡任务的列表，需要具体实现
  //   // 这里只是示例，实际逻辑取决于你的应用需求
  //   return []; // 示例数据
  // }

  // async removeCheckInTask(taskId: number): Promise<void> {
  //   // 找到对应的任务并删除它
  //   await this.checkInRepository.delete(taskId);
  // }

  // async reverseCheckIn(checkInId: number): Promise<CheckIn> {
  //   // 找到打卡记录，并改变其状态，这里需要你提供具体的实现
  //   return; // 示例数据
  // }
}
