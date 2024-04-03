import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { CheckIn, CheckInTask } from '@/entities/checkIn.entity'; // 假设你已经创建了CheckIn实体
import { CreateTaskDTO } from '../dto/check-in-task.dto';

@Injectable()
export class CheckInTaskService {
  constructor(
    @InjectRepository(CheckIn)
    private readonly checkInRepository: Repository<CheckIn>,
    @InjectRepository(CheckInTask)
    private readonly taskRepository: Repository<CheckInTask>,
  ) {}

  async createCheckInTask(taskDto: CreateTaskDTO): Promise<CheckInTask> {
    // 保存打卡任务实体到数据库
    return this.taskRepository.manager.transaction(async (manager) => {
      const task = manager.create(CheckInTask, taskDto);
      const newTask = await manager.save(task);

      // 创建初始打卡记录
      const currentDate = new Date();
      // 设置期限为次日午夜
      currentDate.setDate(currentDate.getDate() + 1);
      currentDate.setHours(0, 0, 0, 0);

      const initialCheckIn = this.checkInRepository.create({
        userId: newTask.userId,
        taskId: newTask.id,
        expiryTime: currentDate,
      });
      await manager.save(initialCheckIn);

      return newTask;
    });
  }

  /**
   * create daily task
   * @param CheckInTask
   * @returns CheckIn
   */
  async createDailyTask(checkInTask: CheckInTask): Promise<CheckIn> {
    const currentDate = new Date();
    // 设置期限为次日午夜
    currentDate.setDate(currentDate.getDate() + 1);
    currentDate.setHours(0, 0, 0, 0);

    const initialCheckIn = this.checkInRepository.create({
      userId: checkInTask.userId,
      taskId: checkInTask.id,
      expiryTime: currentDate,
    });
    return await this.checkInRepository.save(initialCheckIn);
  }
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
}
